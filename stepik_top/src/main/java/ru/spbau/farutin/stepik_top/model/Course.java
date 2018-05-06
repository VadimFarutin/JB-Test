package ru.spbau.farutin.stepik_top.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Course - POJO class to store course from stepik.
 */
public class Course {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("workload")
    @Expose
    private String workload;
    @SerializedName("cover")
    @Expose
    private Object cover;
    @SerializedName("intro")
    @Expose
    private String intro;
    @SerializedName("course_format")
    @Expose
    private String courseFormat;
    @SerializedName("target_audience")
    @Expose
    private String targetAudience;
    @SerializedName("certificate_footer")
    @Expose
    private Object certificateFooter;
    @SerializedName("certificate_cover_org")
    @Expose
    private Object certificateCoverOrg;
    @SerializedName("is_certificate_auto_issued")
    @Expose
    private boolean isCertificateAutoIssued;
    @SerializedName("certificate_regular_threshold")
    @Expose
    private Object certificateRegularThreshold;
    @SerializedName("certificate_distinction_threshold")
    @Expose
    private Object certificateDistinctionThreshold;
    @SerializedName("instructors")
    @Expose
    private List<Integer> instructors = null;
    @SerializedName("certificate")
    @Expose
    private String certificate;
    @SerializedName("requirements")
    @Expose
    private String requirements;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sections")
    @Expose
    private List<Integer> sections = null;
    @SerializedName("total_units")
    @Expose
    private int totalUnits;
    @SerializedName("enrollment")
    @Expose
    private Object enrollment;
    @SerializedName("is_favorite")
    @Expose
    private boolean isFavorite;
    @SerializedName("actions")
    @Expose
    private Actions actions;
    @SerializedName("progress")
    @Expose
    private Object progress;
    @SerializedName("first_lesson")
    @Expose
    private int firstLesson;
    @SerializedName("first_unit")
    @Expose
    private int firstUnit;
    @SerializedName("certificate_link")
    @Expose
    private Object certificateLink;
    @SerializedName("certificate_regular_link")
    @Expose
    private Object certificateRegularLink;
    @SerializedName("certificate_distinction_link")
    @Expose
    private Object certificateDistinctionLink;
    @SerializedName("schedule_link")
    @Expose
    private Object scheduleLink;
    @SerializedName("schedule_long_link")
    @Expose
    private Object scheduleLongLink;
    @SerializedName("first_deadline")
    @Expose
    private Object firstDeadline;
    @SerializedName("last_deadline")
    @Expose
    private Object lastDeadline;
    @SerializedName("subscriptions")
    @Expose
    private List<String> subscriptions = null;
    @SerializedName("announcements")
    @Expose
    private List<Object> announcements = null;
    @SerializedName("is_contest")
    @Expose
    private boolean isContest;
    @SerializedName("is_self_paced")
    @Expose
    private boolean isSelfPaced;
    @SerializedName("is_adaptive")
    @Expose
    private boolean isAdaptive;
    @SerializedName("is_idea_compatible")
    @Expose
    private boolean isIdeaCompatible;
    @SerializedName("last_step")
    @Expose
    private String lastStep;
    @SerializedName("intro_video")
    @Expose
    private Object introVideo;
    @SerializedName("social_providers")
    @Expose
    private List<Object> socialProviders = null;
    @SerializedName("authors")
    @Expose
    private List<Integer> authors = null;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("has_tutors")
    @Expose
    private boolean hasTutors;
    @SerializedName("is_promoted")
    @Expose
    private boolean isPromoted;
    @SerializedName("is_enabled")
    @Expose
    private boolean isEnabled;
    @SerializedName("is_proctored")
    @Expose
    private boolean isProctored;
    @SerializedName("proctor_url")
    @Expose
    private Object proctorUrl;
    @SerializedName("review_summary")
    @Expose
    private int reviewSummary;
    @SerializedName("schedule_type")
    @Expose
    private String scheduleType;
    @SerializedName("certificates_count")
    @Expose
    private int certificatesCount;
    @SerializedName("learners_count")
    @Expose
    private int learnersCount;
    @SerializedName("time_to_complete")
    @Expose
    private Object timeToComplete;
    @SerializedName("is_popular")
    @Expose
    private boolean isPopular;
    @SerializedName("similar_courses")
    @Expose
    private List<Object> similarCourses = null;
    @SerializedName("is_unsuitable")
    @Expose
    private boolean isUnsuitable;
    @SerializedName("owner")
    @Expose
    private int owner;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("is_featured")
    @Expose
    private boolean isFeatured;
    @SerializedName("is_public")
    @Expose
    private boolean isPublic;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("begin_date")
    @Expose
    private Object beginDate;
    @SerializedName("end_date")
    @Expose
    private Object endDate;
    @SerializedName("soft_deadline")
    @Expose
    private Object softDeadline;
    @SerializedName("hard_deadline")
    @Expose
    private Object hardDeadline;
    @SerializedName("grading_policy")
    @Expose
    private String gradingPolicy;
    @SerializedName("begin_date_source")
    @Expose
    private Object beginDateSource;
    @SerializedName("end_date_source")
    @Expose
    private Object endDateSource;
    @SerializedName("soft_deadline_source")
    @Expose
    private Object softDeadlineSource;
    @SerializedName("hard_deadline_source")
    @Expose
    private Object hardDeadlineSource;
    @SerializedName("grading_policy_source")
    @Expose
    private String gradingPolicySource;
    @SerializedName("is_active")
    @Expose
    private boolean isActive;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("update_date")
    @Expose
    private String updateDate;
    @SerializedName("learners_group")
    @Expose
    private Object learnersGroup;
    @SerializedName("testers_group")
    @Expose
    private Object testersGroup;
    @SerializedName("moderators_group")
    @Expose
    private Object moderatorsGroup;
    @SerializedName("teachers_group")
    @Expose
    private Object teachersGroup;
    @SerializedName("admins_group")
    @Expose
    private Object adminsGroup;
    @SerializedName("discussions_count")
    @Expose
    private int discussionsCount;
    @SerializedName("discussion_proxy")
    @Expose
    private Object discussionProxy;
    @SerializedName("discussion_threads")
    @Expose
    private List<Object> discussionThreads = null;
    @SerializedName("lti_consumer_key")
    @Expose
    private String ltiConsumerKey;
    @SerializedName("lti_secret_key")
    @Expose
    private String ltiSecretKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWorkload() {
        return workload;
    }

    public void setWorkload(String workload) {
        this.workload = workload;
    }

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCourseFormat() {
        return courseFormat;
    }

    public void setCourseFormat(String courseFormat) {
        this.courseFormat = courseFormat;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public Object getCertificateFooter() {
        return certificateFooter;
    }

    public void setCertificateFooter(Object certificateFooter) {
        this.certificateFooter = certificateFooter;
    }

    public Object getCertificateCoverOrg() {
        return certificateCoverOrg;
    }

    public void setCertificateCoverOrg(Object certificateCoverOrg) {
        this.certificateCoverOrg = certificateCoverOrg;
    }

    public boolean isIsCertificateAutoIssued() {
        return isCertificateAutoIssued;
    }

    public void setIsCertificateAutoIssued(boolean isCertificateAutoIssued) {
        this.isCertificateAutoIssued = isCertificateAutoIssued;
    }

    public Object getCertificateRegularThreshold() {
        return certificateRegularThreshold;
    }

    public void setCertificateRegularThreshold(Object certificateRegularThreshold) {
        this.certificateRegularThreshold = certificateRegularThreshold;
    }

    public Object getCertificateDistinctionThreshold() {
        return certificateDistinctionThreshold;
    }

    public void setCertificateDistinctionThreshold(Object certificateDistinctionThreshold) {
        this.certificateDistinctionThreshold = certificateDistinctionThreshold;
    }

    public List<Integer> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Integer> instructors) {
        this.instructors = instructors;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getSections() {
        return sections;
    }

    public void setSections(List<Integer> sections) {
        this.sections = sections;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    public Object getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Object enrollment) {
        this.enrollment = enrollment;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public Object getProgress() {
        return progress;
    }

    public void setProgress(Object progress) {
        this.progress = progress;
    }

    public int getFirstLesson() {
        return firstLesson;
    }

    public void setFirstLesson(int firstLesson) {
        this.firstLesson = firstLesson;
    }

    public int getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(int firstUnit) {
        this.firstUnit = firstUnit;
    }

    public Object getCertificateLink() {
        return certificateLink;
    }

    public void setCertificateLink(Object certificateLink) {
        this.certificateLink = certificateLink;
    }

    public Object getCertificateRegularLink() {
        return certificateRegularLink;
    }

    public void setCertificateRegularLink(Object certificateRegularLink) {
        this.certificateRegularLink = certificateRegularLink;
    }

    public Object getCertificateDistinctionLink() {
        return certificateDistinctionLink;
    }

    public void setCertificateDistinctionLink(Object certificateDistinctionLink) {
        this.certificateDistinctionLink = certificateDistinctionLink;
    }

    public Object getScheduleLink() {
        return scheduleLink;
    }

    public void setScheduleLink(Object scheduleLink) {
        this.scheduleLink = scheduleLink;
    }

    public Object getScheduleLongLink() {
        return scheduleLongLink;
    }

    public void setScheduleLongLink(Object scheduleLongLink) {
        this.scheduleLongLink = scheduleLongLink;
    }

    public Object getFirstDeadline() {
        return firstDeadline;
    }

    public void setFirstDeadline(Object firstDeadline) {
        this.firstDeadline = firstDeadline;
    }

    public Object getLastDeadline() {
        return lastDeadline;
    }

    public void setLastDeadline(Object lastDeadline) {
        this.lastDeadline = lastDeadline;
    }

    public List<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<String> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Object> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Object> announcements) {
        this.announcements = announcements;
    }

    public boolean isIsContest() {
        return isContest;
    }

    public void setIsContest(boolean isContest) {
        this.isContest = isContest;
    }

    public boolean isIsSelfPaced() {
        return isSelfPaced;
    }

    public void setIsSelfPaced(boolean isSelfPaced) {
        this.isSelfPaced = isSelfPaced;
    }

    public boolean isIsAdaptive() {
        return isAdaptive;
    }

    public void setIsAdaptive(boolean isAdaptive) {
        this.isAdaptive = isAdaptive;
    }

    public boolean isIsIdeaCompatible() {
        return isIdeaCompatible;
    }

    public void setIsIdeaCompatible(boolean isIdeaCompatible) {
        this.isIdeaCompatible = isIdeaCompatible;
    }

    public String getLastStep() {
        return lastStep;
    }

    public void setLastStep(String lastStep) {
        this.lastStep = lastStep;
    }

    public Object getIntroVideo() {
        return introVideo;
    }

    public void setIntroVideo(Object introVideo) {
        this.introVideo = introVideo;
    }

    public List<Object> getSocialProviders() {
        return socialProviders;
    }

    public void setSocialProviders(List<Object> socialProviders) {
        this.socialProviders = socialProviders;
    }

    public List<Integer> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Integer> authors) {
        this.authors = authors;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public boolean isHasTutors() {
        return hasTutors;
    }

    public void setHasTutors(boolean hasTutors) {
        this.hasTutors = hasTutors;
    }

    public boolean isIsPromoted() {
        return isPromoted;
    }

    public void setIsPromoted(boolean isPromoted) {
        this.isPromoted = isPromoted;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isIsProctored() {
        return isProctored;
    }

    public void setIsProctored(boolean isProctored) {
        this.isProctored = isProctored;
    }

    public Object getProctorUrl() {
        return proctorUrl;
    }

    public void setProctorUrl(Object proctorUrl) {
        this.proctorUrl = proctorUrl;
    }

    public int getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(int reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public int getCertificatesCount() {
        return certificatesCount;
    }

    public void setCertificatesCount(int certificatesCount) {
        this.certificatesCount = certificatesCount;
    }

    public int getLearnersCount() {
        return learnersCount;
    }

    public void setLearnersCount(int learnersCount) {
        this.learnersCount = learnersCount;
    }

    public Object getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(Object timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public boolean isIsPopular() {
        return isPopular;
    }

    public void setIsPopular(boolean isPopular) {
        this.isPopular = isPopular;
    }

    public List<Object> getSimilarCourses() {
        return similarCourses;
    }

    public void setSimilarCourses(List<Object> similarCourses) {
        this.similarCourses = similarCourses;
    }

    public boolean isIsUnsuitable() {
        return isUnsuitable;
    }

    public void setIsUnsuitable(boolean isUnsuitable) {
        this.isUnsuitable = isUnsuitable;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Object getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Object beginDate) {
        this.beginDate = beginDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Object getSoftDeadline() {
        return softDeadline;
    }

    public void setSoftDeadline(Object softDeadline) {
        this.softDeadline = softDeadline;
    }

    public Object getHardDeadline() {
        return hardDeadline;
    }

    public void setHardDeadline(Object hardDeadline) {
        this.hardDeadline = hardDeadline;
    }

    public String getGradingPolicy() {
        return gradingPolicy;
    }

    public void setGradingPolicy(String gradingPolicy) {
        this.gradingPolicy = gradingPolicy;
    }

    public Object getBeginDateSource() {
        return beginDateSource;
    }

    public void setBeginDateSource(Object beginDateSource) {
        this.beginDateSource = beginDateSource;
    }

    public Object getEndDateSource() {
        return endDateSource;
    }

    public void setEndDateSource(Object endDateSource) {
        this.endDateSource = endDateSource;
    }

    public Object getSoftDeadlineSource() {
        return softDeadlineSource;
    }

    public void setSoftDeadlineSource(Object softDeadlineSource) {
        this.softDeadlineSource = softDeadlineSource;
    }

    public Object getHardDeadlineSource() {
        return hardDeadlineSource;
    }

    public void setHardDeadlineSource(Object hardDeadlineSource) {
        this.hardDeadlineSource = hardDeadlineSource;
    }

    public String getGradingPolicySource() {
        return gradingPolicySource;
    }

    public void setGradingPolicySource(String gradingPolicySource) {
        this.gradingPolicySource = gradingPolicySource;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Object getLearnersGroup() {
        return learnersGroup;
    }

    public void setLearnersGroup(Object learnersGroup) {
        this.learnersGroup = learnersGroup;
    }

    public Object getTestersGroup() {
        return testersGroup;
    }

    public void setTestersGroup(Object testersGroup) {
        this.testersGroup = testersGroup;
    }

    public Object getModeratorsGroup() {
        return moderatorsGroup;
    }

    public void setModeratorsGroup(Object moderatorsGroup) {
        this.moderatorsGroup = moderatorsGroup;
    }

    public Object getTeachersGroup() {
        return teachersGroup;
    }

    public void setTeachersGroup(Object teachersGroup) {
        this.teachersGroup = teachersGroup;
    }

    public Object getAdminsGroup() {
        return adminsGroup;
    }

    public void setAdminsGroup(Object adminsGroup) {
        this.adminsGroup = adminsGroup;
    }

    public int getDiscussionsCount() {
        return discussionsCount;
    }

    public void setDiscussionsCount(int discussionsCount) {
        this.discussionsCount = discussionsCount;
    }

    public Object getDiscussionProxy() {
        return discussionProxy;
    }

    public void setDiscussionProxy(Object discussionProxy) {
        this.discussionProxy = discussionProxy;
    }

    public List<Object> getDiscussionThreads() {
        return discussionThreads;
    }

    public void setDiscussionThreads(List<Object> discussionThreads) {
        this.discussionThreads = discussionThreads;
    }

    public String getLtiConsumerKey() {
        return ltiConsumerKey;
    }

    public void setLtiConsumerKey(String ltiConsumerKey) {
        this.ltiConsumerKey = ltiConsumerKey;
    }

    public String getLtiSecretKey() {
        return ltiSecretKey;
    }

    public void setLtiSecretKey(String ltiSecretKey) {
        this.ltiSecretKey = ltiSecretKey;
    }
}
