package study.study.board.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.board.entity.Post

interface BoardRepository : JpaRepository<Post, Long>