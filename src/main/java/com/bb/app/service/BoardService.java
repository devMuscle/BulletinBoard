package com.bb.app.service;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import com.bb.app.repository.BoardRepository;
import com.bb.app.repository.VoteBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    BoardRepository repository;
    @Autowired
    VoteBoardRepository vrepository;

    //trateBoard 서비스
    public void writeBoard(BoardEntity board){
        repository.save(board);
    }
    public List<BoardEntity> findByMemberId(long memberId){
        List<BoardEntity> board = repository.findByMember_Id(memberId);
        return board;
    }
    public Optional<BoardEntity> TradeboardView(long bno){
        Optional<BoardEntity> board = repository.findById(bno);
        return  board;
    }
    public Optional<BoardEntity> TradeboardUpdate(long bno){
        Optional<BoardEntity> board = repository.findById(bno);
        return  board;
    }
    public void BoardUpdate(BoardEntity board){ repository.save(board);}
    public void BoardDelete(Long id){
        repository.deleteById(id);
    }
    public List<BoardEntity> TradeboardList(){
        List<BoardEntity> board = repository.findAll();
        return board;
    }
    //voteBoard 서비스
    public void writeVoteBoard(VoteBoardEntity board){
        vrepository.save(board);
    }
    public List<VoteBoardEntity> findByVMemberId(long memberId){
        List<VoteBoardEntity> board = vrepository.findByMember_Id(memberId);
        return board;
    }
    public Optional<VoteBoardEntity> VoteboardView(long bno){
        Optional<VoteBoardEntity> board = vrepository.findById(bno);
        return  board;
    }
    public Optional<VoteBoardEntity> VoteboardUpdate(long bno){
        Optional<VoteBoardEntity> board = vrepository.findById(bno);
        return  board;
    }
    public void VoteBoardUpdate(VoteBoardEntity board){ vrepository.save(board);}
    public void VoteBoardDelete(Long id){
        vrepository.deleteById(id);
    }
    public List<VoteBoardEntity> VoteboardList(){
        List<VoteBoardEntity> board = vrepository.findAll();
        return board;
    }
}
