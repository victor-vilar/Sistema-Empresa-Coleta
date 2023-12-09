import { ScheduleType } from '../enums/Schedule';
import { WeekdayType } from "../enums/Weekday";

export interface CollectionFrequency {

  days:WeekdayType[];
  schedule:ScheduleType;

}
