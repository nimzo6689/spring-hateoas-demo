package demo.hateoas.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.hateoas.domain.Article;
import demo.hateoas.presentation.model.UserModel;
import demo.hateoas.service.ArticleService;
import demo.hateoas.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ArticleService articleService;

    public UserController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public EntityModel<UserModel> getUser(@PathVariable Long id) {

        var user = userService.findById(id);
        var userModel = new UserModel(user);

        userModel.add(
                linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getArticles(id)).withRel("articles"));

        return EntityModel.of(userModel);
    }

    @GetMapping("/{userId}/articles")
    public CollectionModel<Article> getArticles(@PathVariable Long userId) {

        var articles = this.articleService.findByUserId(userId);
        return CollectionModel.of(articles);
    }
}
