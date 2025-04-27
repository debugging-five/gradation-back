package com.app.gradationback.mapper;

import com.app.gradationback.domain.GradationExhibitionDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExhibitionMapper {

    public void selectGradation(GradationExhibitionDTO gradationExhibitionDTO);

}
