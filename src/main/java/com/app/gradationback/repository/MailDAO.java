package com.app.gradationback.repository;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.mapper.MailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MailDAO {

    private final MailMapper mailMapper;

//    이메일로 아이디 찾기
    public Optional<MailDTO> findByIdForEmail(String receiveUserEmail) {
        return mailMapper.findByIdForEmail(receiveUserEmail);
    }

//    쪽지 등록
    public void save(MailDTO mailDTO) {
        mailMapper.insert(mailDTO);
    }

//    받은쪽지 리스트
    public List<MailDTO> getReceivedList(Long receiveUserId) {
        return mailMapper.selectReceived(receiveUserId);
    }

//    보낸쪽지 리스트
    public List<MailDTO> getSendedList(Long sendUserId) {
        return mailMapper.selectSended(sendUserId);
    }

//    받은쪽지 단일조회
    public Optional<MailDTO> findReceivedOne(Long id, Long receviedUserId) {
        return mailMapper.selectReceivedDetail(id, receviedUserId);
    }

//    보낸쪽지 단일조회
    public Optional<MailDTO> findSendedOne(Long id, Long sendUserId) {
        return mailMapper.selectReceivedDetail(id, sendUserId);
    }

//    받은쪽지 삭제
    public void deleteReceived(Long id) {
        mailMapper.deleteReceivedMail(id);
    }

//    보낸쪽지 삭제
    public void deleteSended(Long id) {
        mailMapper.deleteSendedMail(id);
    }

//    알림리스트
    public List<MailDTO> getAlertList(Long receiveUserId) {
        return mailMapper.selectAlert(receiveUserId);
    }

//    알림상세
    public Optional<MailDTO> findByDetail(Long id) {
        return mailMapper.selectDetail(id);
    }

//    읽음처리
    public void readUpdate(Long id, Long receiveUserId) {
        mailMapper.readUpdate(id, receiveUserId);
    }

//    읽지않은 메일 갯수 카운트
    public int countNotRead(Long receiveUserId) {
        return mailMapper.countNotRead(receiveUserId);
    }

}
