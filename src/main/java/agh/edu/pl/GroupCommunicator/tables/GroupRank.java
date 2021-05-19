package agh.edu.pl.GroupCommunicator.tables;

public enum GroupRank {
    ADMIN, MODERATOR, MEMBER;

    @Override
    public String toString() {
        String result;
        switch (this) {
            case ADMIN:
                result = "Admin";
                break;
            case MODERATOR:
                result = "Moderator";
                break;
            case MEMBER:
                result = "Member";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        };
        return result;
    }
}
