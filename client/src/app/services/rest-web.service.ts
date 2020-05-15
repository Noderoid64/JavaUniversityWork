import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Motorcycle } from '../model/motorcycle.model';

@Injectable({ providedIn: 'root' })
export class RestWebService {

    private httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          'Access-Control-Allow-Origin': '*'
        })
      };

    constructor(private http: HttpClient) {

    }

    public getAllMotorcycles(): Observable<Motorcycle[]> {
        return this.http.get<Motorcycle[]>('motorcycle/all', this.httpOptions);
    }

    public addNewRecord(title: string, type: number, price: number, year: Date, engineSize: number, mileage: number): Observable<boolean> {
      return this.http.post<boolean>('motorcycle/new?title=' + title + '&type=' + type + '&year=' + year.toLocaleDateString() + '&engineSize=' + engineSize + '&mileage=' + mileage + '&price=' + price, null);
    }

    public getTypes(): Observable<Map<number, string>> {
      return this.http.get<Map<number,string>>('motorcycle/types', this.httpOptions);
    }

}