package net.testaholic.brewery.data.greendao;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table FLASHCARDS.
 */
public class FlashcardEntity implements java.io.Serializable {

    private Long id;
    /** Not-null value. */
    private String word;
    /** Not-null value. */
    private String definition;
    private int status;
    private Long lessonId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient FlashcardEntityDao myDao;

    private LessonEntity lessonEntity;
    private Long lessonEntity__resolvedKey;


    public FlashcardEntity() {
    }

    public FlashcardEntity(Long id) {
        this.id = id;
    }

    public FlashcardEntity(Long id, String word, String definition, int status, Long lessonId) {
        this.id = id;
        this.word = word;
        this.definition = definition;
        this.status = status;
        this.lessonId = lessonId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFlashcardEntityDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getWord() {
        return word;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setWord(String word) {
        this.word = word;
    }

    /** Not-null value. */
    public String getDefinition() {
        return definition;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    /** To-one relationship, resolved on first access. */
    public LessonEntity getLessonEntity() {
        Long __key = this.lessonId;
        if (lessonEntity__resolvedKey == null || !lessonEntity__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LessonEntityDao targetDao = daoSession.getLessonEntityDao();
            LessonEntity lessonEntityNew = targetDao.load(__key);
            synchronized (this) {
                lessonEntity = lessonEntityNew;
            	lessonEntity__resolvedKey = __key;
            }
        }
        return lessonEntity;
    }

    public void setLessonEntity(LessonEntity lessonEntity) {
        synchronized (this) {
            this.lessonEntity = lessonEntity;
            lessonId = lessonEntity == null ? null : lessonEntity.getId();
            lessonEntity__resolvedKey = lessonId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
