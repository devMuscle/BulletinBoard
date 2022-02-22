package com.bb.app.service;

import com.bb.app.DTO.MemberDto;
import com.bb.app.DTO.MyBoardDto;
import com.bb.app.Mapper.BoardMapper;
import com.bb.app.Mapper.MemberMapper;
import com.bb.app.Mapper.MyBoardMapper;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.exception.DeleteException;
import com.bb.app.exception.DuplicatedIdException;
import com.bb.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final MyBoardMapper myBoardMapper;

    public void signup(MemberDto memberDto) {
        MemberEntity memberEntity = memberMapper.toEntity(memberDto);
        memberRepository.save(memberEntity);
    }

    public void idCheck(String loginId) throws DuplicatedIdException {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);

        if(opMember.isPresent()) {
            throw new DuplicatedIdException("이미 존재하는 id 입니다.");
        }
    }

    public MemberDto login(String id, String pwd) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginIdAndPassword(id, pwd);
        MemberEntity memberEntity = opMember.orElseThrow();
        MemberDto memberDto = memberMapper.toDto(memberEntity);
        return memberDto;
    }

    @Transactional(readOnly = true)
    public MemberDto findMemberInfo(String loginId) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        MemberDto memberDto = memberMapper.toDto(memberEntity);
        return memberDto;
    }
    public void memberUpdate(String loginId, MemberDto memberDto) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        if(!memberDto.getPassword().equals("")) {
            memberEntity.UpdatePassword(memberDto.getPassword());
        }
        if(!memberDto.getNickName().equals("")) {
            memberEntity.UpdateNickname(memberDto.getNickName());
        }
        if(!memberDto.getEmail().equals("")) {
            memberEntity.UpdateEmail(memberDto.getEmail());
        }
    }
    public void memberDelete(String id) throws DeleteException{
        Optional<MemberEntity> member = memberRepository.findByLoginId(id);
        member.orElseThrow(() -> new DeleteException("삭제 실패"));
        memberRepository.deleteByLoginId(id);
    }

    public List<MyBoardDto> findMyBoards(String loginId) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        List<BoardEntity> boardEntityList = memberEntity.getBoardList();
        List<MyBoardDto> myBoardDtoList = myBoardMapper.toMyBoardDtoList(boardEntityList);
        return myBoardDtoList;
    }
}
