package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DisplayMapper {

//    전시 등록
    public void insertArt(ArtVO artVO);

//    전시 이미지 등록
    public void insertArtImg(ArtImgVO artImgVO);

//    전시 리스트
    public List<ArtPostDTO> selectAll();

//    전시 상세
    public Optional<ArtPostDTO> select(Long id);
}
