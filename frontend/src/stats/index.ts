import fs from 'fs';
import {CsvFileReader} from "@/stats/CsvFileReader";
import {MatchResult} from "@/stats/MatchResult";
import {MatchCsvFileReader} from "@/stats/MatchCsvFileReader";

let matchContent =  new MatchCsvFileReader('docs/football.csv');
matchContent.read();

let matches:string[][]   = matchContent.data as string[][];

let manUnitedWins = 0;


for (let match of matches) {

    if (match[1]=== "Man United" && match[5] === MatchResult.HomeWin) {
        manUnitedWins++;
    }
    else if (match[2] === "Man United" && match[5] === MatchResult.AwayWin) {
        manUnitedWins++;
    }

}