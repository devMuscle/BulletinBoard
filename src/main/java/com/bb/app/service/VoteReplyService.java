package com.bb.app.service;

import com.bb.app.DTO.VoteReplyDto;
import com.bb.app.Mapper.VoteReplyMapper;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import com.bb.app.entity.VoteReplyEntity;
import com.bb.app.repository.BoardRepository;
import com.bb.app.repository.VoteBoardRepository;
import com.bb.app.repository.VoteReplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteReplyService {


    final VoteReplyRepository replyRepository;
    final VoteBoardRepository voteBoardRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    public VoteReplyService(VoteReplyRepository replyRepository, VoteBoardRepository voteBoardRepository){
        this.replyRepository = replyRepository;
        this.voteBoardRepository = voteBoardRepository;
    }
    @Transactional
    public void writeBoardReply(VoteReplyDto reply) {
        VoteReplyEntity replyEntity = VoteReplyMapper.INSTANCE.toEntity(reply);
        Optional<VoteBoardEntity> board = voteBoardRepository.findById(reply.getVoteBoardId());
        VoteBoardEntity boardEntity = board.get();
        boardEntity.UpdateVoteReplyList(replyEntity);

    }

//    public VoteReplyEntity FindCommentById(long id) {
//        Optional<VoteReplyEntity> b = repository.findById(id);
//        VoteReplyEntity voteReply = b.get();
//
//        return voteReply;
//    }

    @Transactional
    public void UpdateBoardReply(long commentNo, VoteReplyDto newVoteReply) {
        Optional<VoteReplyEntity> voteReply = replyRepository.findById(commentNo);
        VoteReplyEntity voteReplyEntity = voteReply.get();
        String newComment = newVoteReply.getComment();
        voteReplyEntity.UpdateComment(newComment);

        logger.info("서비스 내용 확인: " + commentNo);
        logger.info("서비스 내용 확인: " + newComment);

    }

    public void DeleteVoteReply(long replyNo) {
        replyRepository.deleteById(replyNo);
    }
}
