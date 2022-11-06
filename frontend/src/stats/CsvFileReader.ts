import fs from "fs";

export abstract class CsvFileReader<T>{
  abstract data : T[];
  protected constructor(public filename: string) {}
  read(): void {
    this.data = fs
      .readFileSync(this.filename, {
        encoding: 'utf-8'
      })
      .split('\n')
      .map((row: string): string[] => {
        return row.split(',');
      }).map(this.getRowDataFormatted);

  }
  abstract getRowDataFormatted(row: string[]): T;
}

