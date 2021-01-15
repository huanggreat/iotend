package com.iotend.gql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.iotend.gql.model.Post;
import com.iotend.gql.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostQuery implements GraphQLQueryResolver {
    @Autowired
    private PostRepository postRepository;

    public List<Post> posts() {
        return postRepository.findAll();
    }

    public Optional<Post> post(String id) {
        return postRepository.findById(id);
    }
}
