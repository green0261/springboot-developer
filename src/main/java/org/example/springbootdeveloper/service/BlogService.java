package org.example.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.repository.BlogRepository;
import org.example.springbootdeveloper.dto.AddArticleRequest;
import org.example.springbootdeveloper.dto.UpdateArticleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service //클래스를 빈으로 서블릿 컨테이너에 등록해준다
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        //save(): jparepository에서 지원하는 저장 메서드. addarticlerequest 클래스에 저장된 값을 article 데이터베이스에 저장한다
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found:"+id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional //트랜잭션 메서드
    //트랜잭션: 데이터베이스에서 데이터를 바꾸기 위해 묶은 작업의 단위
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: "+ id));

        article.update(request.getTitle(), request.getContent());

        return article;

    }
}
