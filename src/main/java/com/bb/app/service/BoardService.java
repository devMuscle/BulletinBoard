package com.bb.app.service;

import com.bb.app.DTO.BoardDto;
import com.bb.app.DTO.VoteBoardDto;
import com.bb.app.Mapper.BoardMapper;
import com.bb.app.Mapper.VoteBoardMapper;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.VoteBoardEntity;
import com.bb.app.repository.BoardRepository;
import com.bb.app.repository.MemberRepository;
import com.bb.app.repository.VoteBoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final VoteBoardRepository vBoardRepository;
    private final MemberRepository memberRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    public BoardService(BoardRepository boardRepository, VoteBoardRepository vBoardRepository, MemberRepository memberRepository){
        this.boardRepository = boardRepository;
        this.vBoardRepository = vBoardRepository;
        this.memberRepository = memberRepository;
    }

    //trateBoard 서비스
    @Transactional
    public void writeBoard(BoardDto boardDto){
        BoardEntity boardEntity = BoardMapper.INSTANCE.toEntity(boardDto);
        Optional<MemberEntity> member = memberRepository.findById(boardDto.getMember());
        MemberEntity memberEntity = member.get();
        memberEntity.UpdateboardList(boardEntity);

    }

    public BoardDto TradeboardView(long bno){
        Optional<BoardEntity> board = boardRepository.findById(bno);
        BoardEntity boardEntity = board.get();
        BoardDto boardDto = BoardMapper.INSTANCE.toDto(boardEntity);
        return  boardDto;
    }
    @Transactional
    public void TradeboardUpdate(long bno, BoardDto boardDto){
        Optional<BoardEntity> board = boardRepository.findById(bno);
        BoardEntity boardUpdate = board.get();

        boardUpdate.UpdateContent(boardDto.getContent());
        boardUpdate.UpdateTile(boardDto.getTitle());

    }
    @Transactional
    public void BoardDelete(Long id){
        boardRepository.deleteById(id);
    }
    public List<BoardDto> TradeboardList(){
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDto> boardList = BoardMapper.INSTANCE.toDtoList(entityList);
        return boardList;
    }



    //voteBoard 서비스
    @Transactional
    public void writeVoteBoard(VoteBoardDto boardDto){
        VoteBoardEntity boardEntity = VoteBoardMapper.INSTANCE.toEntity(boardDto);
        Optional<MemberEntity> member = memberRepository.findById(boardDto.getMember());
        MemberEntity memberEntity = member.get();
        memberEntity.UpdatevoteBoardList(boardEntity);

    }
    public VoteBoardDto VoteboardView(long bno){
        Optional<VoteBoardEntity> board = vBoardRepository.findById(bno);
        VoteBoardEntity boardEntity = board.get();
        VoteBoardDto boardDto = VoteBoardMapper.INSTANCE.toDto(boardEntity);
        return  boardDto;
    }
    @Transactional
    public void VoteboardUpdate(long bno, VoteBoardDto board){
        Optional<VoteBoardEntity> boardEntity = vBoardRepository.findById(bno);
        VoteBoardEntity boardUpdate = boardEntity.get();

        boardUpdate.UpdateTile(board.getTitle());
        boardUpdate.UpdateContent(board.getContent());
    }
    @Transactional
    public void VoteBoardDelete(Long id){
        vBoardRepository.deleteById(id);
    }
    public List<VoteBoardDto> VoteboardList(){
        List<VoteBoardEntity> entityList = vBoardRepository.findAll();
        List<VoteBoardDto> boardList = VoteBoardMapper.INSTANCE.toDtoList(entityList);
        return boardList;
    }
}
