package com.bb.app.service;

import com.bb.app.DTO.*;
import com.bb.app.Mapper.*;
import com.bb.app.constant.AttachHandler;
import com.bb.app.entity.*;
import com.bb.app.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.File;
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
    private final VoteAttachRepository voteAttachRepository;
    private final BoardAttachRepository boardAttachRepository;
    private final BoardReplyRepository boardReplyRepository;
    private final VoteReplyRepository voteReplyRepository;

    private final AttachHandler attachHandler;

    private final EntityManager entityManager;

    private final BoardDetailMapper boardDetailMapper;
    private final VoteBoardDetailMapper voteBoardDetailMapper;
    private final ResBoardReplyMapper resBoardReplyMapper;
    private final VoteReplyMapper voteReplyMapper;

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
        Optional<BoardEntity> board = boardRepository.findBoardWithAttach(bno);
        BoardEntity boardEntity = board.get();
        BoardDetailDto boardDetailDto = boardDetailMapper.toDto(boardEntity);

        //게시글 첨부 추가 부분
        if(boardEntity.getAttach().size()==0) {
            //Long defaultImageId = 37L;

            //Optional<BoardAttachEntity> boardAttach = boardAttachRepository.findById(defaultImageId);
            //String defaultImagePath = boardAttach.get().getFilePath();
            //String defaultFileName = boardAttach.get().getFileName();
            String defaultImagePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "default.png";
            String defaultFileName = "default.png";


            boardDetailDto.insertDefaultImagePath(defaultImagePath);
            boardDetailDto.insertDefaultFileName(defaultFileName);
        } else {
            boardDetailDto.insertImagePath(boardEntity.getAttach());
            boardDetailDto.insertFileName(boardEntity.getAttach());
        }

        //게시글 댓글 추가 부분
        List<BoardReplyEntity> boardReplyEntityList = boardReplyRepository.selectAllByBoardNo(bno);
        List<ResBoardReplyDto> resBoardReplyDtoList = resBoardReplyMapper.toDtoList(boardReplyEntityList);

        boardDetailDto.insertResBoardReply(resBoardReplyDtoList);
        
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
        List<BoardEntity> entityList = boardRepository.findAllBoardWithAttach();
        List<BoardDto> boardList = new ArrayList<>();

        for(BoardEntity board : entityList) {
            if(board.getAttach().size()==0) {
                /*
                Long defaultImageId = 37L;

                Optional<BoardAttachEntity> boardAttach = boardAttachRepository.findById(defaultImageId);
                String defaultImagePath = boardAttach.get().getFilePath();
                String defaultFileName = boardAttach.get().getFileName();

                 */

                String defaultImagePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "default.png";
                String defaultFileName = "default.png";

                BoardDto boardDto = BoardMapper.INSTANCE.toDto(board);
                boardDto.insertImagePath(defaultImagePath);
                boardDto.insertFileName(defaultFileName);

                boardList.add(boardDto);
            } else {
                String imagePath = board.getAttach().get(0).getFilePath();
                String fileName = board.getAttach().get(0).getFileName();

                BoardDto boardDto = BoardMapper.INSTANCE.toDto(board);
                boardDto.insertImagePath(imagePath);
                boardDto.insertFileName(fileName);

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

        vBoardRepository.save(voteBoardEntity);


        Optional<MemberEntity> member = memberRepository.findById(voteBoardDto.getMember());

        MemberEntity memberEntity = member.get();
        memberEntity.UpdatevoteBoardList(voteBoardEntity);



    }

    @Transactional(readOnly = true)
    public VoteBoardDetailDto VoteboardView(long bno){
        Optional<VoteBoardEntity> voteBoard = vBoardRepository.findVoteWithAttachById(bno);
        VoteBoardEntity voteBoardEntity = voteBoard.get();
        VoteBoardDetailDto voteBoardDetailDto = voteBoardDetailMapper.toDto(voteBoardEntity);

        if(voteBoardEntity.getAttach().size()==0) {
            /*
            Long defaultImageId = 28L;

            Optional<VoteAttachEntity> voteAttach = voteAttachRepository.findById(defaultImageId);
            String defaultImagePath = voteAttach.get().getFilePath();
            String defaultFileName = voteAttach.get().getFileName();
             */
            String defaultImagePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "default.png";
            String defaultFileName = "default.png";

            voteBoardDetailDto.insertDefaultImagePath(defaultImagePath);
            voteBoardDetailDto.insertDefaultFileName(defaultFileName);
        } else {
            voteBoardDetailDto.insertImagePath(voteBoardEntity.getAttach());
            voteBoardDetailDto.insertFileName(voteBoardEntity.getAttach());
        }

        //게시글 댓글 추가 부분
        List<VoteReplyEntity> voteReplyEntityList = voteReplyRepository.selectAllByBoardNo(bno);
        List<VoteReplyDto> voteReplyDtoList = voteReplyMapper.toDto(voteReplyEntityList);

        voteBoardDetailDto.insertResBoardReply(voteReplyDtoList);

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
                /*
                Long defaultImageId = 28L;

                Optional<VoteAttachEntity> voteAttach = voteAttachRepository.findById(defaultImageId);
                String defaultImagePath = voteAttach.get().getFilePath();
                String defauiltFileName = voteAttach.get().getFileName();

                 */
                String defaultImagePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "default.png";
                String defaultFileName = "default.png";


                VoteBoardDto voteBoardDto = VoteBoardMapper.INSTANCE.toDto(voteBoard);
                voteBoardDto.insertImagePath(defaultImagePath);
                voteBoardDto.insertFileName(defaultFileName);

                boardList.add(voteBoardDto);
            } else {
                String imagePath = voteBoard.getAttach().get(0).getFilePath();
                String fileName = voteBoard.getAttach().get(0).getFileName();

                VoteBoardDto voteBoardDto = VoteBoardMapper.INSTANCE.toDto(voteBoard);
                voteBoardDto.insertImagePath(imagePath);
                voteBoardDto.insertFileName(fileName);

                boardList.add(voteBoardDto);
            }
        }

        return boardList;
    }
}
