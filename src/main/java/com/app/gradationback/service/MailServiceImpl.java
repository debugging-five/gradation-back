package com.app.gradationback.service;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.repository.MailDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailDAO mailDAO;

//    이메일로 아이디 찾기
    @Override
    public Optional<MailDTO> findByIdForEmail(String receiveUserEmail) {
        return mailDAO.findByIdForEmail(receiveUserEmail);
    }

//    등록
    @Override
    public void register(MailDTO mailDTO) {
        mailDAO.save(mailDTO);
    }

//    받은 쪽지 리스트
    @Override
    public List<MailDTO> getReceivedList(Long receiveUserId) {
        return mailDAO.getReceivedList(receiveUserId);
    }

//    보낸 쪽지 리스트
    @Override
    public List<MailDTO> getSendedList(Long sendUserId) {
        return mailDAO.getSendedList(sendUserId);
    }

//    받은 쪽지 단일조회
    @Override
    public Optional<MailDTO> findReceivedOne(Long id, Long receiveUserId) {
        return mailDAO.findReceivedOne(id, receiveUserId);
    }

//    보낸쪽지 단일조회
    @Override
    public Optional<MailDTO>findSendedOne(Long id, Long sendUserId) {
        return mailDAO.findSendedOne(id, sendUserId);
    }

//    받은 메시지 삭제
    @Override
    public void removeReceivedMail(Long id) {
        mailDAO.deleteReceived(id);
    }

//    보낸 메시지 삭제
    @Override
    public void removeSendedMail(Long id) {
        mailDAO.deleteSended(id);
    }

//    알림리스트
    @Override
    public List<MailDTO> getAlertList(Long receiveUserId) {
        return mailDAO.getAlertList(receiveUserId);
    }

//    알림상세
    public Optional<MailDTO> findByDetail(Long id) {
        return mailDAO.findByDetail(id);
    }

//    읽음처리
    public void readUpdate (Long id, Long receiveUserId) {
        mailDAO.readUpdate(id, receiveUserId);
    }

//    읽지않은 메일 갯수 카운트
    public int countNotRead(Long receiveUserId) {
        return mailDAO.countNotRead(receiveUserId);
    }

}
