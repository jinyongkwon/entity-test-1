package site.metacoding.entitytest.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDetailRespDto {
    private Integer id;
    private String title;
    private String content;
    private boolean isLove;

    // class BoardDto{
    // private Integer id;
    // private String title;
    // private String content;
    // }
}
