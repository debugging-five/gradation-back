package com.app.gradationback.service;

import com.app.gradationback.domain.MailDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MailService {
//    이메일로 아이디 찾기
    public Optional<MailDTO> findByIdForEmail(String receiveUserEmail);

//    쪽지등록
    public void register(MailDTO mailDTO);

//    받은쪽지 리스트
    public List<MailDTO> getReceivedList(Long receiveUserId);

//    보낸쪽지 리스트
    public List<MailDTO> getSendedList(Long sendUserId);

//    받은쪽지 단일조회
    public Optional<MailDTO> findReceivedOne(Long id, Long receiveUserId);

//    보낸쪽지 단일조회
    public Optional<MailDTO> findSendedOne(Long id, Long sendUserId);

//    받은쪽지 삭제
    public void removeReceivedMail(Long id, Long receiveUserId);

//    보낸쪽지 삭제
    public void removeSendedMail(Long id, Long sendUserId);

//    알림리스트
    public List<MailDTO> getAlertList(Long receiveUserId);

//    알림상세
    public Optional<MailDTO> findAlertOne(Long id, Long receiveUserId);

//    읽음처리
    public void readUpdate(Long id, Long receiveUserId);

//    읽지않은 메일 갯수 카운트
    public int countNotRead(Long receiveUserId);

}
