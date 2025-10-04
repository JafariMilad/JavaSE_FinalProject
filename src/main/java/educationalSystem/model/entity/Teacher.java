package educationalSystem.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Teacher {

        private int teacherCode;
        private Lesson lesson;

        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }

