package backend.wedstagram.service.CommentService;

import backend.wedstagram.domain.Comment;
import backend.wedstagram.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public void insertComment(Comment comment){
        commentRepository.save(comment);
    }

}
