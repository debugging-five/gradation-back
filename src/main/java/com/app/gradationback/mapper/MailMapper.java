package com.app.gradationback.mapper;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.domain.MailVO;
import com.app.gradationback.domain.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MailMapper {

//    이메일로 아이디 찾기
    public Optional<MailDTO> findByIdForEmail(String receiveUserEmail);

//    쪽지 보내기
    public void insert (MailDTO mailDTO);

//    쪽지 수신함
    public List<MailDTO> selectReceived(Long id);

//    내가 보낸 쪽지
    public List<MailDTO> selectSended(Long id);

//    받은 쪽지 단일조회
    public Optional<MailDTO> selectReceivedDetail(Long id, Long receiveUserId);

//    보낸 쪽지 단일조회
    public Optional<MailDTO> selectSendedDetail(Long id, Long sendUserId);

//    내가 보낸 쪽지 삭제
    public void deleteSendedMail(Long id , Long sendUserId);

//    받은 쪽지 삭제
    public void deleteReceivedMail(Long id, Long receiveUserId);

//    알림리스트
    public List<MailDTO> selectAlert(Long receiveUserId);

//    알림상세
    public Optional<MailDTO> selectAlertDetail(Long id, Long receiveUserId);

//    읽음처리
    public void readUpdate (Long id, Long receiveUserId);

//    읽지않은 메일 갯수 카운트
    public int countNotRead(Long receiveUserId);
}
