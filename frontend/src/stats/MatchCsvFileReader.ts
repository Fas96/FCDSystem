import {MatchData, MatchResult} from "@/stats/MatchResult";
import {dateStringToDate} from "@/util/DateUtil";
import {CsvFileReader} from "@/stats/CsvFileReader";

export  class MatchCsvFileReader extends CsvFileReader<MatchData>  {
  data: MatchData[] = [];

  getRowDataFormatted(row: string[]): MatchData {
    return [ dateStringToDate(row[0]), row[1], row[2], parseInt(row[3]), parseInt(row[4]), row[5] as MatchResult, row[6]];
  }


}