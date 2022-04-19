package vttp2022.paf.Workshop26.repository;

public class Queries {

    public static final String SQL_INSERT_POST =
        "insert into post(photo, comment, poster, mediatype) values(?, ?, ?, ?)";

    public static final String SQL_GET_POST_BY_ID = 
        "select post_id, photo, comment, poster, mediatype from post where post_id = ?";
    
}
