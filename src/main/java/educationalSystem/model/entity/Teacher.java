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

        private String name;
        private String brand;
        private LocalDate manDate;
        private String color;
        private String plate;


        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }
    }

