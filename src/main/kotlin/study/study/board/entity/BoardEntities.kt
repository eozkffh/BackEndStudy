package study.study.board.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_post_id", columnNames = ["postId"])]
)
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = null,

    @Column(nullable = false,length = 30)
    val title: String,

    @Column(nullable = false, columnDefinition = "TEXT")
    val description: String,

    @Column(nullable = false)
    val creationDate: LocalDateTime = LocalDateTime.now(),

    )