package com.bb.app.service;

import com.bb.app.DTO.MemberDto;
import com.bb.app.DTO.MyBoardDto;
import com.bb.app.DTO.MyReplyDto;
import com.bb.app.Mapper.BoardMapper;
import com.bb.app.Mapper.MemberMapper;
import com.bb.app.Mapper.MyBoardMapper;
import com.bb.app.Mapper.MyReplyMapper;
import com.bb.app.entity.*;
import com.bb.app.exception.DeleteException;
import com.bb.app.exception.DuplicatedIdException;
import com.bb.app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final BoardRepository boardRepository;
    private final VoteBoardRepository voteBoardRepository;
    private final BoardReplyRepository boardReplyRepository;
    private final VoteReplyRepository voteReplyRepository;

    private final MemberMapper memberMapper;
    private final MyBoardMapper myBoardMapper;
    private final MyReplyMapper myReplyMapper;

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


    public List<MyBoardDto> findMyTradeBoards(String loginId, Pageable pageable) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        Long memberId = memberEntity.getId();

        List<BoardEntity> boardEntityList = boardRepository.findAllByMemberId(memberId, pageable);

        List<MyBoardDto> myBoardDtoList = myBoardMapper.boardListToMyBoardDtoList(boardEntityList);

        return myBoardDtoList;
    }

    public List<MyBoardDto> findMyVoteBoards(String loginId, Pageable pageable) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        Long memberId = memberEntity.getId();

        List<VoteBoardEntity> voteBoardEntityList = voteBoardRepository.findAllByMemberId(memberId, pageable);
        List<MyBoardDto> myBoardDtoList = myBoardMapper.voteListToMyBoardDtoList(voteBoardEntityList);

        return myBoardDtoList;
    }

    public List<MyReplyDto> findMyBoardReply(String loginId, Pageable pageable) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        Long memberId = memberEntity.getId();

        List<BoardReplyEntity> boardReplyEntityList = boardReplyRepository.findAllByMemberId(memberId, pageable);
        List<MyReplyDto> myReplyDtoList = myReplyMapper.boardReplyToDtoList(boardReplyEntityList);

        return myReplyDtoList;
    }

    public List<MyReplyDto> findMyVoteReply(String loginId, Pageable pageable) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        Long memberId = memberEntity.getId();

        List<VoteReplyEntity> voteReplyEntityList = voteReplyRepository.findAllByMemberId(memberId, pageable);
        List<MyReplyDto> myReplyDtoList = myReplyMapper.voteReplyToDtoList(voteReplyEntityList);

        return myReplyDtoList;
    }


}
