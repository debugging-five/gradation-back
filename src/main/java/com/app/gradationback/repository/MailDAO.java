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

//    쪽지 등록
    public void registraction(MailDTO mailDTO) {
        mailMapper.insert(mailDTO);
    }

//    받은쪽지 리스트
    public List<MailDTO> receivedList(Long receiveUserId) {
        return mailMapper.selectReceived(receiveUserId);
    }

//    보낸쪽지 리스트
    public List<MailDTO> sendedList(Long sendUserId) {
        return mailMapper.selectSended(sendUserId);
    }

//    받은쪽지 단일조회
    public Optional<MailDTO> receivedOne(Long id, Long receiveUserId) {
        return mailMapper.selectReceivedDetail(id, receiveUserId);
    }

//    보낸쪽지 단일조회
    public Optional<MailDTO> sendedOne(Long id, Long sendUserId) {
        return mailMapper.selectReceivedDetail(id, sendUserId);
    }

//    받은쪽지 삭제
    public void deleteReceivedMail(Long id, Long receiveUserId) {
        mailMapper.deleteReceivedMail(id, receiveUserId);
    }

//    보낸쪽지 삭제
    public void deleteSendedMail(Long id, Long sendUserId) {
        mailMapper.deleteSendedMail(id, sendUserId);
    }

}
