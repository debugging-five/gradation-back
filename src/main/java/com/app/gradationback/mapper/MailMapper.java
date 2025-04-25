package com.app.gradationback.mapper;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.domain.MailVO;
import com.app.gradationback.domain.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MailMapper {

//    쪽지 보내기
    public void insert (MailDTO mailDTO);

//    쪽지 수신함
    public List<MailDTO> selectReceived(Long id);

//    내가 보낸 쪽지
    public List<MailDTO> selectSended(Long id);

}
