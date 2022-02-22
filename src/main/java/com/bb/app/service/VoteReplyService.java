package com.bb.app.service;

import com.bb.app.DTO.VoteReplyDto;
import com.bb.app.Mapper.VoteReplyMapper;
import com.bb.app.entity.VoteReplyEntity;
import com.bb.app.repository.VoteReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteReplyService {


    final VoteReplyRepository repository;
    public VoteReplyService(VoteReplyRepository repository){
        this.repository = repository;
    }
    @Transactional
    public void writeBoardReply(VoteReplyDto reply) {
        VoteReplyEntity replyEntity = VoteReplyMapper.INSTANCE.toEntity(reply);
    }

//    public VoteReplyEntity FindCommentById(long id) {
//        Optional<VoteReplyEntity> b = repository.findById(id);
//        VoteReplyEntity voteReply = b.get();
//
//        return voteReply;
//    }
//
//    public Optional<VoteReplyEntity> UpdateBoardReply(VoteReplyEntity voteReply) {
//        repository.save(voteReply);
//        Optional<VoteReplyEntity> updatedVoteReply = repository.findById(voteReply.getId());
//
//        return updatedVoteReply;
//    }
//
//    public void DeleteVoteReply(long replyNo) {
//        repository.deleteById(replyNo);
//    }
}
