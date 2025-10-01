package educationalSystem.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Teacher {

        private int id;
        private int teacherCode;
        private Lesson lesson;
        private int userId ;


        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }

