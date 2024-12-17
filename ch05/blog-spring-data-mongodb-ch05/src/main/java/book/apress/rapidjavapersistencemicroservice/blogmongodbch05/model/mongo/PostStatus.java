package book.apress.rapidjavapersistencemicroservice.blogmongodbch05.model.mongo;

public enum PostStatus {

    ACTIVE(1), NOT_ACTIVE(2);

    final int status;

    PostStatus(int status) {
        this.status = status;
    }
}
