package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtFilterVO;
import com.app.gradationback.domain.ArtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ArtMapper {

//    작품 등록
    public void insert(ArtVO artVO);

//    전체 작품 조회
    public List<ArtVO> selectAll();

//    단일 작품 조회
    public Optional<ArtVO> select(Long id);

//    카테고리별 작품 조회
//    public List<ArtVO> selectArtListByFilter(ArtFilterVO artFilterVO);



//    작품 삭제
    public void deleteById(Long id);
}
