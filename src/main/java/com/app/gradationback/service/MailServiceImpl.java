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
    public void removeReceivedMail(Long id, Long receiveUserId) {
        mailDAO.deleteReceived(id, receiveUserId);
    }

//    보낸 메시지 삭제
    @Override
    public void removeSendedMail(Long id, Long sendUserId) {
        mailDAO.deleteSended(id, sendUserId);
    }
}
