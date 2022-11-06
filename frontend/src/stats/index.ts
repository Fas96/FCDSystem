import fs from 'fs';
import {CsvFileReader} from "@/stats/CsvFileReader";

let matchContent =  new CsvFileReader('docs/football.csv');
matchContent.read();

let matches:string[][]   = matchContent.data as string[][];

let manUnitedWins = 0;
enum MatchResult {
    HomeWin = 'H',
    AwayWin = 'A',
    Draw = 'D'
}

for (let match of matches) {

    if (match[1]=== "Man United" && match[5] === MatchResult.HomeWin) {
        manUnitedWins++;
    }
    else if (match[2] === "Man United" && match[5] === MatchResult.AwayWin) {
        manUnitedWins++;
    }

}