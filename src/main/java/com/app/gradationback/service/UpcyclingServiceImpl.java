package com.app.gradationback.service;

import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.repository.UpcyclingDAO;
import com.app.gradationback.util.FileSaveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UpcyclingServiceImpl implements UpcyclingService {

    private final UpcyclingDAO upcyclingDAO;

    @Override
    public List<UpcyclingDTO> getUpcyclingUserList(Long userId) {
        return upcyclingDAO.getUpcyclingUserList(userId);
    }

    @Override
    public Optional<UpcyclingVO> getByUpcyclingUser(Long id) {
        return upcyclingDAO.getByUpcyclingUser(id);
    }

    @Override
    public void register(UpcyclingVO upcyclingVO, MultipartFile file) {
        try {
//          파일 저장 경로, 이름 설정
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "_" + file.getOriginalFilename();
            String filePath = "upcycling";
//          파일 저장
            FileSaveUtil fileSaveUtil = new FileSaveUtil();
            fileSaveUtil.fileSave(file, filePath, fileName);
//          VO에 파일 정보 세팅
            upcyclingVO.setUpcyclingImgName(fileName);
            upcyclingVO.setUpcyclingImgPath("C:/upload/" + filePath);
//          DB저장
            upcyclingDAO.save(upcyclingVO);
        }catch (Exception e){
            throw new RuntimeException("파일 저장에 실패하였습니다.", e);
        }
    }

    @Override
    public void modify(UpcyclingVO upcyclingVO) {
        upcyclingDAO.update(upcyclingVO);
    }

    @Override
    public void remove(Long id) {
        upcyclingDAO.delete(id);
    }
}
