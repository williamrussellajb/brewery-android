/*
 * Copyright (C) dbychkov.com.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.testaholic.brewery.presentation;

import net.testaholic.brewery.bus.CreateBookmarkEvent;
import net.testaholic.brewery.bus.RemoveBookmarkEvent;
import net.testaholic.brewery.bus.RxEventBus;
import net.testaholic.brewery.domain.repository.LessonRepository;
import net.testaholic.brewery.thread.PostExecutionThread;
import net.testaholic.brewery.thread.ThreadExecutor;

/**
 * Presenter for {@link net.testaholic.brewery.fragment.BundledLessonsTabFragment}
 */
public class BundledLessonsTabFragmentPresenter extends LessonsPresenter {

    private LessonRepository lessonRepository;

    public BundledLessonsTabFragmentPresenter(ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread, LessonRepository lessonRepository, RxEventBus rxEventBus) {
        super(threadExecutor, postExecutionThread, lessonRepository.getBundledLessons(), rxEventBus);
        this.lessonRepository = lessonRepository;
        registerBookmarkedStateChangedEventListener();
    }

    private void registerBookmarkedStateChangedEventListener() {
        execute(rxEventBus.toObservable(), new DefaultSubscriber<Object>() {

            @Override
            public void onNext(Object event) {
                if (event instanceof RemoveBookmarkEvent) {
                    reloadLessonList();
                }
            }
        });
    }

    public void bookmarkedLessonClicked(Long lessonId, final int position) {
        execute(lessonRepository.bookmarkLesson(lessonId), new DefaultSubscriber<Boolean>() {

            @Override
            public void onNext(Boolean bookmarked) {
                renderLessonsView.renderLessonItemBookmarked(position, bookmarked);
                // Send event to adjacent tabs
                rxEventBus.send(new CreateBookmarkEvent());
            }
        });

    }
}
