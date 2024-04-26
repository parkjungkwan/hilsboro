//package philoarte.jaemin.api.crawling.domain;
//
//import lombok.Data;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Data
//@Entity
//@Table(name = "reviews")
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "review_id")
//    private Long reviewId;
//
//    @Column
//    private String category;
//
//    @Column
//    private String title;
//
//    @Column
//    private String address;
//
//    @Column
//    private String hashtag;
//
//    @Column
//    private String content;
//
//
//    public String toString(){
//        return " , " + hashtag + " , " +
//                " \n";
//    }
//}