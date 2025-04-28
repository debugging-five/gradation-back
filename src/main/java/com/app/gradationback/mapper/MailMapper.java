package com.app.gradationback.mapper;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.domain.MailVO;
import com.app.gradationback.domain.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MailMapper {

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
    public Optional<MailDTO> deleteSendedMail(Long id , Long sendUserId);

//    받은 쪽지 삭제
    public Optional<MailDTO> deleteReceivedMail(Long id, Long receiveUserId);

}
