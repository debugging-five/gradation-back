package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtImgVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtImgMapper {

//    작품 이미지 등록
    public void insert(ArtImgVO artImgVO);

//    전체 작품 이미지 조회
    public List<ArtImgVO> selectAll();

//    단일 작품 이미지 조회
    public Optional<ArtImgVO> select(Long id);

//    작품 이미지 전체 삭제
    public void deleteAllByArtId(Long artId);
}
