package com.hailey.spring.service.posts;

import com.hailey.spring.domain.posts.Posts;
import com.hailey.spring.domain.posts.PostsRepository;
import com.hailey.spring.web.dto.PostsResponseDto;
import com.hailey.spring.web.dto.PostsSaveRequestDto;
import com.hailey.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private  final PostsRepository postsRepository;
    //등록
    @Transactional
    public Long save(PostsSaveRequestDto requestsDto) {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }
    //수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    //조회
    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
