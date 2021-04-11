package com.hailey.spring.service;

import com.hailey.spring.domain.posts.Posts;
import com.hailey.spring.domain.posts.PostsRepository;
import com.hailey.spring.web.dto.PostsListResponseDto;
import com.hailey.spring.web.dto.PostsResponseDto;
import com.hailey.spring.web.dto.PostsSaveRequestDto;
import com.hailey.spring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    //글 목록조회 (글 전체 리스트 보기)
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    //삭제
    @Transactional
    public void delete(long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException(("해당 게시글이 없습니다. id="+id)));
        postsRepository.delete(posts);
    }
}
