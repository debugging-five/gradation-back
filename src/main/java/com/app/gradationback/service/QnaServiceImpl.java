package com.app.gradationback.service;

import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import com.app.gradationback.repository.QnaDAO;
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
public class QnaServiceImpl implements QnaService {

    private final QnaDAO qnaDAO;

//    등록
    @Override
    public void registraction(QnaVO qnaVO, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                String fileName = uuid + "_" + file.getOriginalFilename();
                String filePath = "images/qna";

                FileSaveUtil fileSaveUtil = new FileSaveUtil();
                fileSaveUtil.fileSave(file, filePath, fileName);

                qnaVO.setQnaImgName(fileName);
                qnaVO.setQnaImgPath("C:/upload/" + filePath);
            }

            qnaDAO.save(qnaVO);
        } catch (Exception e) {
            throw new RuntimeException("파일 저장에 실패하였습니다.", e);
        }
    }

//    단일조회
    @Override
    public Optional<QnaDTO> getQna(Long id) {
        return qnaDAO.findById(id);
    }

//    목록
    @Override
    public List<QnaDTO> getQnaList(String userEmail) {
        return qnaDAO.findAll(userEmail);
    }

//    수정
    @Override
    public void modify(QnaVO qnaVO) {
        qnaDAO.update(qnaVO);
    }

//    삭제
    @Override
    public void remove(Long id) {
        qnaDAO.delete(id);
    }

//    관리자용 전체 문의 내역 조회
    @Override
    public List<QnaDTO> getAllQnaListForAdmin() {
        return qnaDAO.getAllQnaListForAdmin();
    }
}

