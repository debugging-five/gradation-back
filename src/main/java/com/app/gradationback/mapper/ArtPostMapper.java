package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.domain.ArtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtPostMapper {

//    작품 게시
    public void insert(ArtPostVO artPostVO);

//    작품 게시 상세 (전체)
    public List<ArtPostDTO> selectAll();

//    작품 게시 상세 (단일)
    public Optional<ArtPostDTO> select(Long id);

//    작품 게시 수정
    public void update(ArtPostVO artPostVO);

//    작품 게시 삭제
    public void delete(Long id);







}
