package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtImgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtImgMapper {

//    작품 이미지 등록
    public void insert(ArtImgVO artImgVO);

//    작품 이미지 조회 (작품 ID로)
    public List<ArtImgVO> selectAllByArtId(Long artId);

//    작품 이미지 전체 삭제 (작품 ID로)
    public void deleteAllByArtId(Long artId);
}
