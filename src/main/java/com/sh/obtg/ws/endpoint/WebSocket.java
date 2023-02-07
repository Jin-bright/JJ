package com.sh.obtg.ws.endpoint;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.sh.obtg.notification.model.dto.Notification;
import com.sh.obtg.ws.MessageType;
import com.sh.obtg.ws.config.WebSocketConfigurator;


@ServerEndpoint(value = "/websocket", configurator = WebSocketConfigurator.class)  //이설정클래스를이용하겠다
public class WebSocket {

	public static Map<String, Session> clientMap = Collections.synchronizedMap(new HashMap<>());
	public static Map<String, Set<String>> chatParticipantMap = Collections.synchronizedMap( new HashMap<>() );//채팅방 
	// - Key - string chatroomId  -- 사람id 아님 !!!! -- 정수다 
	// - value  - set<String> chatMembers   
	
	
	//receiver찾기
	
	
	//★★★★ 혜진
	public void peoplechecklog() {
		System.out.printf("🌳현재접속자수🌳: (%d명) ],  %s%n", clientMap.size(), clientMap.keySet() );
		System.out.printf("🌳현재 채팅방 수🌳 : (%d개)] : %s%n", chatParticipantMap.size(),  chatParticipantMap );

	}
	
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		Map<String, Object> userProp = config.getUserProperties(); //아이디 들어있다 
		//★★★혜진-chatroomid 꺼내 
		String memberId = (String) userProp.get("memberId");
		String chatroomId =  (String) userProp.get("chatroomId");
		String chatreceiver =  (String) userProp.get("chatreceiver");
		////////////////////////////
		
		List<Notification> notiList = (List<Notification>)userProp.get("notiList");
		
		// 우리서버에 누가 접속하고 있는지 관리
		clientMap.put(memberId, session); 
		peoplechecklog();//현재접속자 수 
		
		// session설정맵에 사용자아이디 추가 (close때 사용)
		Map<String, Object> sessionUserProp = session.getUserProperties();
		sessionUserProp.put("memberId", memberId);
		
		// 이거 왜 자꾸 NullPointerException 이거뜨네,,, (해결^^)
		if(notiList != null) {
			sessionUserProp.put("notiList", notiList);
			getNotiList(notiList, session);
		}
		

	 	/////////////////////////★★★혜진-chatroomid 꺼내 
		//System.out.println("chatroomid 뭔데 ( userProp.get 하면나와야댐 ) " +  chatroomId  );
		

/**		
		if( chatroomId != null  ) {
			sessionUserProp.put("chatroomId", chatroomId);
			System.out.println("chatroomid 뭔데  put 해주라" +  chatroomId  );
			addToChatroom(chatroomId, memberId, chatreceiver);
			sendChatroomEnterMsg(chatroomId, memberId, chatreceiver, session); // 채팅방에 누가 입장햇다 
		}
**/
		
	}
	
	//★★★★ 혜진 1.채팅방들어간다 - 채팅방별 사용자 관리한다 
/**	
	private void addToChatroom(String chatroomId, String memberId, String chatreceiver) {

		Set<String> participantSet = chatParticipantMap.get(chatroomId);  // 반환값 = 사람 멤버 id
		//map.get(chatroomId) 하면 반환되는건 사람memberID
		
		//인간이 없는 첫번째경우임   
		if( participantSet == null ) {
			participantSet = new HashSet<>();
			chatParticipantMap.put( chatroomId, participantSet);
			
		}
		participantSet.add(memberId); //set에다 추가하는거임 = 이 set은 사람 id넣는 set임 
		System.out.println("**🌳participantSet(인간들..?)🌳 : " + participantSet  );	
	}
	
	//★★★★ 혜진 2. 채팅방에서 나갈때 
	private void removeFromChatroom(String chatroomId, String memberId) {
		Set<String> participantSet = chatParticipantMap.get(chatroomId); // 반환값 = 사람 멤버 id
		participantSet.remove(memberId);
		
		//이거하면 아예 안남는거아닌가 ?
		if( participantSet.size() == 0 ) { //만약 이 사용자가 마지막 사용자라면 채팅방까지 지운다 
			chatParticipantMap.remove(chatroomId);
		}
		
	}
	
	//혜진3. 채팅방에 들어간거
	private void sendChatroomEnterMsg(String chatroomId, String memberId, String chatreceiver, Session session) {

		Map<String,Object> data = new HashMap<>();
		data.put("chatroomId", chatroomId);
		data.put("sender", memberId);
		data.put("messageType", MessageType.CHATROOM_ENTER);
		data.put("datetime", System.currentTimeMillis());
		data.put(chatreceiver, chatreceiver);
		onMessage( new Gson().toJson(data), session );
		// onMessage(new Gson().toJson(data), session);
	}
**/	
	
	// 알림 내역 꺼내기
	private void getNotiList(List<Notification> notiList, Session session) {
		for(Notification noti : notiList) {
			Map<String, Object> data = new HashMap<>();
			System.out.println("다시 돌아오니?");
			data.put("receiver", noti.getReceiver());
			data.put("messageType", MessageType.NOTIFICATION);
			data.put("message", noti.getMessage());
			data.put("datetime", noti.getDatetime());
			System.out.println("데이타까지 왔니?" + data);
			onMessage(new Gson().toJson(data), session);
		}
	}

	@OnMessage
	public void onMessage(String msg, Session session){
		System.out.println("msg@onMessage = " + msg);
		
		// 넘어온 msg(json)를 Map.class 형식으로 변환
		Map<String, Object> data = new Gson().fromJson(msg, Map.class);

		//혜진 
		String chatroomId = (String) data.get("chatroomId");
		MessageType messageType = MessageType.valueOf((String) data.get("messageType"));
	
		
		
		
		switch(messageType) {
		case NOTIFICATION :
			System.out.println("여기옴?");
			if(session != null) {
				Basic basic = session.getBasicRemote();
				try {
					basic.sendText(new Gson().toJson(data));// json 문자열로 변환되어서 던짐	
				} catch (IOException e) {
					e.printStackTrace();
				} 
			};
			break;
		case HELLOCHATWORLD :	
			Collection<Session> hellochatworldSessions =  clientMap.values();//현재접속중인 웹소켓을 가져온거다
			System.out.println( "🌳hellochatworldSessions🌳 : " +  hellochatworldSessions);
			for( Session  chatsess : hellochatworldSessions ) {
				Basic basic  = chatsess.getBasicRemote();  //basic객체  . 웹소켓 세션에 출력객체 
				try {
					basic.sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			break;
			
		case CHATROOM_ENTER :
				//채팅방사용자목록 
			Set<String> participantSet = chatParticipantMap.get(chatroomId);
			for(String participant : participantSet) {
				Session chatsess = clientMap.get(participant);
				
				Basic basic  = chatsess.getBasicRemote();  //basic객체  . 웹소켓 세션에 출력객체 
				try {
					basic.sendText(msg);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			break;
			
		}//
			
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace(); // 에러방지
		//에러나면보여주라
	}
	
	
	//session에도 userprop이있고-close때 사용(=sessionUserProp), endpoint에도 있음 
	@OnClose
	public void onClose(Session session) {
		Map<String, Object> sessionUserProp = session.getUserProperties();
		String memberId = (String) sessionUserProp.get("memberId");
		//★★★혜진
		String chatroomId = (String)sessionUserProp.get("chatroomId"); //지워
		String chatreceiver =  (String) sessionUserProp.get("chatreceiver");	 //다지워
		// 웹소켓 세션 관리 맵에서 제거
		clientMap.remove(memberId);
		
		
		//★★★혜진 - 관리맵에서 채팅방제거
	//	if( chatroomId != null ) {
		//	removeFromChatroom(chatroomId, memberId); //채팅방에서 채팅방id, memberid 제거 	
		//	sendChatroomLeaveMsg(chatroomId, memberId, session); // 떠나는거 알리는 메시지 
	//	}
		
		System.out.println("==== 이제 클로즈 ==== ");
		peoplechecklog();
	}	
}
