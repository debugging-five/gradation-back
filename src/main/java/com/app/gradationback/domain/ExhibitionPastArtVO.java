package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ExhibitionPastArtVO {
    private Long id;
    private Long gradationExhibitionId;
    private Long artId;
}
