import {MatchData} from "@/stats/MatchResult";
import fs from "fs";

interface DataReader<T> {
    readData(): Promise<T>;
    data: T;
}



class MatchReader{
    matches: MatchData[] = [];
    constructor(public reader: DataReader<MatchData[]>) {}
    load(): void {
        this.reader.readData().then((data: MatchData[]) => {
            this.matches = data;
        });
    }
}

class CsvFileReader  implements DataReader<MatchData[]> {
    data: MatchData[] = [];
    constructor(public filename: string) {}
    readData(): Promise<MatchData[]> {
        return new Promise((resolve, reject) => {
            fs.readFile(this.filename, {
                encoding: 'utf-8'
            }, (err, data) => {
                if (err) {
                    reject(err);
                }
                this.data = data.split('\n').map((row: string): string[] => {
                    return row.split(',');
                }) as MatchData[];
                resolve(this.data);
            });
        });
    }
}

const csvFileReader = new CsvFileReader('docs/football.csv')
const matchReader = new MatchReader(csvFileReader);
matchReader.load();
console.log(matchReader.matches);

