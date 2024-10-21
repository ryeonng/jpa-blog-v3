package com.tenco.blog_v2.user;

import com.tenco.blog_v2.common.errors.Exception400;
import com.tenco.blog_v2.common.errors.Exception401;
import com.tenco.blog_v2.common.errors.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    // @Autowired
    private final UserJPARepository userJPARepository;

    /**
     * 회원가입 서비스
     */
    public void signUp(UserDTO.JoinDTO reqDto) {
        // 1. username기반으로 가입 <- 유니크 제약 - 확인
        Optional<User> userOp = userJPARepository.findByUsername(reqDto.getUsername());
        if (userOp.isPresent()) { // 값이 있다
            throw new Exception400("중복된 username 입니다.");
        }
        
        // 회원 가입
        userJPARepository.save(reqDto.toEntity());
    }

    /**
     * 로그인 서비스
     */
    public User signIn(UserDTO.LoginDTO reqDTO) {
        User sessionUser = userJPARepository
                .findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow( () -> new Exception401("인증되지 않은 사용자 입니다."));
        return sessionUser;
    }

    /**
     * 회원 정보 조회 서비스
     */
    public User readUser(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow( () -> new Exception404("회원정보를 찾을 수 없습니다."));
        return user;
    }

    /**
     * 회원 정보 수정 서비스
     */
    @Transactional
    public User updateUser(int id, UserDTO.UpdateDTO reqDTO) {
        // 1. 사용자 조회 및 예외 처리
        User user = userJPARepository.findById(id)
                .orElseThrow( () -> new Exception404("회원정보를 찾을 수 없습니다."));

        // 2. 사용자 정보 수정
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());

        // 더티 체킹을 통해 변경사항 자동반영
        return user;
    }
}
