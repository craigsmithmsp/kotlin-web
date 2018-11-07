package com.activeradar.kotlinweb

import com.activeradar.kotlinweb.model.Article
import com.activeradar.kotlinweb.model.ArticleRepository
import com.activeradar.kotlinweb.model.User
import com.activeradar.kotlinweb.model.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
class RepositoriesTest(@Autowired val entityManager: TestEntityManager,
                       @Autowired val userRepository: UserRepository,
                       @Autowired val articleRepository: ArticleRepository) {
    @Test
    fun `When findById then return Article`() {
        val craig = User("csmith", "Craig", "Smith")
        entityManager.persist(craig)
        val article = Article("Kotlin Web Test Tutorial", "Isn't this special, we have a kotlin web test application", "Lorem ipsum", craig)
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findById(article.id!!)

        Assertions.assertThat(found.get()).isEqualTo(article)
    }

    @Test
    fun `When findById then return User`() {
        val craig = User("csmith", "Craig", "Smith")
        entityManager.persist(craig)
        entityManager.flush()

        val found = userRepository.findById(craig.login)

        Assertions.assertThat(found.get()).isEqualTo(craig)

    }
}