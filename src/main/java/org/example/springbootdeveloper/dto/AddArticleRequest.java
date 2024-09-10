package org.example.springbootdeveloper.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    //빌더 패턴을 사용해 dto를 엔티티로 만들어주는 메서드
    public Article toEntity(){ //생성자를 이용해 객체 생성
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
