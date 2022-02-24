package com.bb.app.service;

import com.bb.app.DTO.BoardDetailDto;
import com.bb.app.DTO.BoardDto;
import com.bb.app.DTO.VoteBoardDetailDto;
import com.bb.app.DTO.VoteBoardDto;
import com.bb.app.Mapper.BoardDetailMapper;
import com.bb.app.Mapper.BoardMapper;
import com.bb.app.Mapper.VoteBoardDetailMapper;
import com.bb.app.Mapper.VoteBoardMapper;
import com.bb.app.constant.AttachHandler;
import com.bb.app.entity.*;
import com.bb.app.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final VoteBoardRepository vBoardRepository;
    private final MemberRepository memberRepository;
    private final AttachHandler attachHandler;
    private final EntityManager entityManager;
    private final VoteAttachRepository voteAttachRepository;
    private final BoardAttachRepository boardAttachRepository;
    private final BoardDetailMapper boardDetailMapper;
    private final VoteBoardDetailMapper voteBoardDetailMapper;

    //trateBoard 서비스
    public void writeBoard(List<MultipartFile> files, BoardDto boardDto) throws Exception {
        BoardEntity boardEntity = BoardMapper.INSTANCE.toEntity(boardDto);
        entityManager.flush();

        List<BoardAttachEntity> boardAttachEntityList = attachHandler.parseFileInfToBoardAttach(files);

        boardEntity.addBoardAttachList(boardAttachEntityList);
        Optional<MemberEntity> member = memberRepository.findById(boardDto.getMember());

        MemberEntity memberEntity = member.get();
        memberEntity.UpdateboardList(boardEntity);
    }

    @Transactional(readOnly = true)
    public BoardDetailDto TradeboardView(long bno){
        Optional<BoardEntity> board = boardRepository.findById(bno);
        BoardEntity boardEntity = board.get();
        BoardDetailDto boardDetailDto = boardDetailMapper.toDto(boardEntity);


        if(boardEntity.getAttach().size()==0) {
            Long defaultImageId = 20L;

            Optional<BoardAttachEntity> boardAttach = boardAttachRepository.findById(defaultImageId);
            String defaultImagePath = boardAttach.get().getFilePath();

            boardDetailDto.insertDefaultImagePath(defaultImagePath);
        } else {
            boardDetailDto.insertImagePath(boardEntity.getAttach());
        }

        return  boardDetailDto;
    }

    public void TradeboardUpdate(long bno, BoardDto boardDto){
        Optional<BoardEntity> board = boardRepository.findById(bno);
        BoardEntity boardUpdate = board.get();

        boardUpdate.UpdateContent(boardDto.getContent());
        boardUpdate.UpdateTile(boardDto.getTitle());

    }

    public void BoardDelete(Long id){
        boardRepository.deleteById(id);
    }

    public List<BoardDto> TradeboardList(){
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDto> boardList = new ArrayList<>();

        for(BoardEntity board : entityList) {
            if(board.getAttach().size()==0) {
                Long defaultImageId = 19L;

                Optional<BoardAttachEntity> boardAttach = boardAttachRepository.findById(defaultImageId);
                String defaultImagePath = boardAttach.get().getFilePath();

                BoardDto boardDto = BoardMapper.INSTANCE.toDto(board);
                boardDto.insertImagePath(defaultImagePath);

                boardList.add(boardDto);
            } else {
                String imagePath = board.getAttach().get(0).getFilePath();

                BoardDto boardDto = BoardMapper.INSTANCE.toDto(board);
                boardDto.insertImagePath(imagePath);

                boardList.add(boardDto);
            }
        }

        return boardList;
    }



    //voteBoard 서비스
    public void writeVoteBoard(List<MultipartFile> files, VoteBoardDto voteBoardDto) throws Exception {
        VoteBoardEntity voteBoardEntity = VoteBoardMapper.INSTANCE.toEntity(voteBoardDto);
        entityManager.flush();

        List<VoteAttachEntity> voteAttachEntityList = attachHandler.parseFileInfoToVoteAttach(files);

        voteBoardEntity.addBoardAttachList(voteAttachEntityList);
        Optional<MemberEntity> member = memberRepository.findById(voteBoardDto.getMember());

        MemberEntity memberEntity = member.get();
        memberEntity.UpdatevoteBoardList(voteBoardEntity);

    }

    @Transactional(readOnly = true)
    public VoteBoardDetailDto VoteboardView(long bno){
        Optional<VoteBoardEntity> voteBoard = vBoardRepository.findById(bno);
        VoteBoardEntity voteBoardEntity = voteBoard.get();
        VoteBoardDetailDto voteBoardDetailDto = voteBoardDetailMapper.toDto(voteBoardEntity);

        if(voteBoardEntity.getAttach().size()==0) {
            Long defaultImageId = 18L;

            Optional<VoteAttachEntity> voteAttach = voteAttachRepository.findById(defaultImageId);
            String defaultImagePath = voteAttach.get().getFilePath();

            voteBoardDetailDto.insertDefaultImagePath(defaultImagePath);
        } else {
            voteBoardDetailDto.insertImagePath(voteBoardEntity.getAttach());
        }

        return  voteBoardDetailDto;
    }

    public void VoteboardUpdate(long bno, VoteBoardDto board){
        Optional<VoteBoardEntity> boardEntity = vBoardRepository.findById(bno);
        VoteBoardEntity boardUpdate = boardEntity.get();

        boardUpdate.UpdateTile(board.getTitle());
        boardUpdate.UpdateContent(board.getContent());
    }

    public void VoteBoardDelete(Long id){
        vBoardRepository.deleteById(id);
    }

    public List<VoteBoardDto> VoteboardList(){
        List<VoteBoardEntity> entityList = vBoardRepository.findAll();
        List<VoteBoardDto> boardList = new ArrayList<>();

        for(VoteBoardEntity voteBoard : entityList) {
            if(voteBoard.getAttach().size()==0) {
                Long defaultImageId = 18L;

                Optional<VoteAttachEntity> voteAttach = voteAttachRepository.findById(defaultImageId);
                String defaultImagePath = voteAttach.get().getFilePath();

                VoteBoardDto voteBoardDto = VoteBoardMapper.INSTANCE.toDto(voteBoard);
                voteBoardDto.insertImagePath(defaultImagePath);

                boardList.add(voteBoardDto);
            } else {
                String imagePath = voteBoard.getAttach().get(0).getFilePath();

                VoteBoardDto voteBoardDto = VoteBoardMapper.INSTANCE.toDto(voteBoard);
                voteBoardDto.insertImagePath(imagePath);

                boardList.add(voteBoardDto);
            }
        }

        return boardList;
    }
}
